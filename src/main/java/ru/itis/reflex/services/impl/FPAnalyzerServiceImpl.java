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

    @Autowired
    FPCacheService fpCacheService;

    @Override
    public void update(User user, byte[] userPhotoBytes) {
        nu.pattern.OpenCV.loadShared();
        Mat mat = Imgcodecs.imdecode(new MatOfByte(userPhotoBytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier classifier = new CascadeClassifier("/FaceDetectionAlgorithms/lbpcascade_frontalface.xml");
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(mat, faceDetections);

        boolean isInsidePhoto = faceDetections.toArray().length > 0 && faceDetections.toArray().length < 2;

        boolean isFlexing = false;

        //TODO ДОБАВИТЬ СЮДА MARGIN
        if (isInsidePhoto) {
            FPInfo userFPInfo = fpCacheService.getFPInfo(user);
            Rect rect = faceDetections.toArray()[0];

            if (rect.y < userFPInfo.getLowerPoint() || rect.height + rect.width > userFPInfo.getWHSum()){
                isFlexing = true;
            }
        }

        fpCacheService.updateFP(user, isInsidePhoto, isFlexing);

    }

    @Override
    public void initialize(User user, byte[] userPhotoBytes) {
        nu.pattern.OpenCV.loadShared();
        Mat mat = Imgcodecs.imdecode(new MatOfByte(userPhotoBytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier classifier = new CascadeClassifier("/FaceDetectionAlgorithms/lbpcascade_frontalface.xml");
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
