package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.reflex.models.PostureData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.repositories.PostureDataRepository;
import ru.itis.reflex.repositories.UserRepository;
import ru.itis.reflex.services.interfaces.FPCacheService;
import ru.itis.reflex.util.FPInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FPCacheServiceImpl implements FPCacheService {

    private Map<User, FPInfo> usersFaceInfo = new HashMap<>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostureDataRepository postureDataRepository;

    @Override
    public FPInfo initializeFP(User user, FPInfo fPInfo) {
        if (usersFaceInfo.containsKey(user)) {
            Optional<PostureData> userPostureDataOptional = postureDataRepository.findOneByUserAndDate(user, new Date(fPInfo.getStartOfFollowing()));
            saveOptionalPostureDataToDB(user, userPostureDataOptional);
            usersFaceInfo.get(user).setWHSum(fPInfo.getWHSum());
            usersFaceInfo.get(user).setLowerPoint(fPInfo.getLowerPoint());
            usersFaceInfo.get(user).setSittingStartTime(System.currentTimeMillis());
            usersFaceInfo.get(user).setLastTimeActivity(System.currentTimeMillis());
            this.resetDataForDB(user);
            this.resetStates(user);

        } else {
            usersFaceInfo.put(user, new FPInfo(fPInfo.getLowerPoint(), fPInfo.getWHSum()));
        }
        System.out.println("init = " + usersFaceInfo.get(user));
        return usersFaceInfo.get(user);
    }

    @Override
    public FPInfo updateFP(User user, boolean isInsidePhoto, boolean isFlexing) {
        FPInfo fpInfo = usersFaceInfo.get(user);
        System.out.println("updateFP in cache: before update " + usersFaceInfo.get(user));
        fpInfo.setLastTimeActivity(System.currentTimeMillis());
        if (isInsidePhoto) {
            //TODO УБРАТЬ ХАРДКОД
            if (fpInfo.getNOfEmptyPhotosInARow() > 3) {
                fpInfo.setSittingStartTime(System.currentTimeMillis());
            }
            fpInfo.setNOfEmptyPhotosInARow(0);
            if (isFlexing) {
                fpInfo.incNOfBadPositionsInARow();
            } else {
                fpInfo.setNOfBadPositionsInARow(0);
                fpInfo.incTotalNOfPositions();
            }
        } else {
            fpInfo.incNOfEmptyPhotosInARow();
        }
        System.out.println("updateFP in cache: after update " + usersFaceInfo.get(user));
        return fpInfo;
    }

    @Override
    public void saveAllUsersFPToDB() {
        for (User user: usersFaceInfo.keySet()){
            saveUserFPToDB(user);
        }
        //TODO ЭТОТ МЕТОД НУЖНО ВЫЗЫВАТЬ ЗА 5 МИНУТ ДО КОНЦА КАЖДОГО ДНЯ
    }

    @Override
    public void saveUserFPToDB(User user) {
        if (usersFaceInfo.containsKey(user)){
            Optional<PostureData> userPostureDataOptional = postureDataRepository.findOneByUserAndDate(user, new Date(usersFaceInfo.get(user).getStartOfFollowing()));
            saveOptionalPostureDataToDB(user, userPostureDataOptional);
            this.resetDataForDB(user);
        }
    }

    @Override
    public int cleanCacheFromNotAliveUsers() {
        //TODO УБРАТЬ ХАРДКОД
        Map<User, FPInfo> newUsersFaceInfo = new HashMap<>();
        saveAllUsersFPToDB();
        for (User user : usersFaceInfo.keySet()) {
            if (!(System.currentTimeMillis() - usersFaceInfo.get(user).getLastTimeActivity() > 1000 * 60 * 60)) {
                newUsersFaceInfo.put(user, usersFaceInfo.get(user));
            }
        }
        int previousSize = usersFaceInfo.size();
        usersFaceInfo = newUsersFaceInfo;
        return previousSize - newUsersFaceInfo.size();
    }

    @Override
    public void deleteUserFromCache(User user) {
        saveUserFPToDB(user);
        usersFaceInfo.remove(user);
    }


    @Override
    public int size() {
        return usersFaceInfo.size();
    }

    @Override
    public boolean isInCache(User user) {
        if (usersFaceInfo.containsKey(user)){
            return true;
        }
        return false;
    }

    @Override
    public FPInfo getFPInfo(User user) {
        return usersFaceInfo.get(user);
    }


    private void saveOptionalPostureDataToDB(User user, Optional<PostureData> userPostureDataOptional){
        if (userPostureDataOptional.isPresent()) {
            PostureData userPostureData = userPostureDataOptional.get();
            userPostureData.setSmoothNum(userPostureData.getSmoothNum() + usersFaceInfo.get(user).getTotalNOfPositions());
            userPostureData.setFlexNum(usersFaceInfo.get(user).getTotalNOfBadPositions() + userPostureData.getFlexNum());
            postureDataRepository.save(userPostureData);
        } else {
            PostureData postureData = PostureData.builder()
                    .date(new Date(usersFaceInfo.get(user).getStartOfFollowing()))
                    .flexNum(usersFaceInfo.get(user).getTotalNOfBadPositions())
                    .smoothNum(usersFaceInfo.get(user).getTotalNOfPositions())
                    .user(user)
                    .build();
            postureDataRepository.save(postureData);
        }
    }

    private void resetStates(User user) {
        FPInfo fpInfo = usersFaceInfo.get(user);
        fpInfo.setNOfBadPositionsInARow(0);
        fpInfo.setNOfEmptyPhotosInARow(0);

    }

    private void resetDataForDB(User user) {
        usersFaceInfo.get(user).setTotalNOfBadPositions(0);
        usersFaceInfo.get(user).setTotalNOfPositions(0);
    }

    @Override
    public String toString(){
        return usersFaceInfo.toString();
    }

}
