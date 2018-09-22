package ru.itis.reflex.services.impl;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.FPAnalyzerService;
import ru.itis.reflex.services.interfaces.FPCacheService;
import ru.itis.reflex.util.FPInfo;

@Service
public class FPAnalyzerServiceImpl implements FPAnalyzerService {
    //TODO ПРОПЕРТИС
    private static final int MAXIMUM_FLEXED_PHOTOS_NUM = 5;
    private static final double DOWN_MARGIN_FACTOR = 1.02;
    private static final double HW_MARGIN_FACTOR = 1.1;

    @Autowired
    FPCacheService fpCacheService;

    @Override
    public void update(User user, byte[] userPhotoBytes) {
        Mat mat = Imgcodecs.imdecode(new MatOfByte(userPhotoBytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //TODO ИЗМЕНИТЬ НА ОТНОСИТЕЛЬНЫЙ ПУТЬ
        CascadeClassifier classifier = new CascadeClassifier("C:\\Bce\\programing\\II Projects\\Hackaton_ReFlex\\ReFlex\\src\\main\\resources\\FaceDetectionAlgorithms\\lbpcascade_frontalface.xml");
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(mat, faceDetections);



        boolean isInsidePhoto = faceDetections.toArray().length > 0 && faceDetections.toArray().length < 2;

        boolean isFlexing = false;


        //TODO ДОБАВИТЬ СЮДА MARGIN
        if (isInsidePhoto) {
            FPInfo userFPInfo = fpCacheService.getFPInfo(user);
            Rect rect = faceDetections.toArray()[0];
            if (rect.y > userFPInfo.getLowerPoint() * DOWN_MARGIN_FACTOR || rect.height + rect.width > userFPInfo.getWHSum() * HW_MARGIN_FACTOR){
                isFlexing = true;
            }
        }

        System.out.println("------updateANALYZER");
        System.out.println("isInsideP = " + isInsidePhoto);
        System.out.println("isFlexing = " + isFlexing);
        System.out.println(faceDetections.toList());
        System.out.println("------");
        fpCacheService.updateFP(user, isInsidePhoto, isFlexing);

    }

    @Override
    public void initialize(User user, byte[] userPhotoBytes) {
        //TODO ЕСЛИ НЕТ ЛИЦ ТО ЕЩЕ РАЗ
        nu.pattern.OpenCV.loadShared();
        Mat mat = Imgcodecs.imdecode(new MatOfByte(userPhotoBytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //TODO ИЗМЕНИТЬ НА ОТНОСИТЕЛЬНЫЙ ПУТЬ
        CascadeClassifier classifier = new CascadeClassifier("C:\\Bce\\programing\\II Projects\\Hackaton_ReFlex\\ReFlex\\src\\main\\resources\\FaceDetectionAlgorithms\\lbpcascade_frontalface.xml");
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(mat, faceDetections);
        fpCacheService.initializeFP(user, new FPInfo(faceDetections.toArray()[0].y, faceDetections.toArray()[0].height + faceDetections.toArray()[0].width));
    }

    @Override
    public boolean isFlexing(User user) {
        FPInfo userFPInfo = fpCacheService.getFPInfo(user);
        return userFPInfo.getNOfBadPositionsInARow() > MAXIMUM_FLEXED_PHOTOS_NUM;
    }

    @Override
    public boolean isInitialized(User user) {
        return fpCacheService.isInCache(user);
    }

    @Override
    public boolean isTired(User user) {
        FPInfo userFPInfo = fpCacheService.getFPInfo(user);
        return ((userFPInfo.getSittingStartTime() - System.currentTimeMillis())/1000/60 > 25);
    }

}
