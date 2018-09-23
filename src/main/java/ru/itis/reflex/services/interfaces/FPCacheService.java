package ru.itis.reflex.services.interfaces;

import ru.itis.reflex.models.User;
import ru.itis.reflex.util.FPInfo;

public interface FPCacheService {

    FPInfo initializeFP(User user, FPInfo FPInfo);

    FPInfo updateFP(User user, boolean isInsidePhoto, boolean isFlexing);

    void saveAllUsersFPToDB();

    void saveUserFPToDB(User user);

    int cleanCacheFromNotAliveUsers();

    void deleteUserFromCache(User user);

    int size();

    boolean isInCache(User user);

    FPInfo getFPInfo(User user);

}






