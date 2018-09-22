package ru.itis.reflex.util;

import java.util.Calendar;
import java.util.Date;

public class FPInfo {
    private int lowerPoint;
    private int lHSum;
    private int nOfBadPositionsInARow;
    private int totalNOfPositions;
    private int totalNOfBadPositions;
    private long sittingStartTime;
    private int nOfEmptyPhotosInARow;
    private long lastTimeActivity;
    private long startOfFollowing;


    public FPInfo(int lowerPoint, int lHSum) {
        this(lowerPoint, lHSum, 0);
    }

    public FPInfo(int lowerPoint, int lHSum, int nOfBadPositionsInARow) {
        this.nOfBadPositionsInARow = nOfBadPositionsInARow;
        this.lowerPoint = lowerPoint;
        this.lHSum = lHSum;
        this.sittingStartTime = System.currentTimeMillis();
        this.totalNOfPositions = nOfBadPositionsInARow;
        this.totalNOfBadPositions = nOfBadPositionsInARow;
        this.nOfEmptyPhotosInARow = 0;
        this.lastTimeActivity = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 12, 0,0);
        this.startOfFollowing = calendar.getTimeInMillis();
    }

    public int getNOfBadPositionsInARow() {
        return nOfBadPositionsInARow;
    }

    public void setNOfBadPositionsInARow(int nOfBadPositionsInARow) {
        this.nOfBadPositionsInARow = nOfBadPositionsInARow;
    }

    public void incNOfBadPositionsInARow() {
        this.nOfBadPositionsInARow++;
        this.totalNOfBadPositions++;
        this.totalNOfPositions++;
    }

    public void incTotalNOfPositions() {
        this.totalNOfPositions++;
    }

    public long getLastTimeActivity() {
        return lastTimeActivity;
    }

    public void setLastTimeActivity(long lastTimeActivity) {
        this.lastTimeActivity = lastTimeActivity;
    }

    public int getLowerPoint() {
        return lowerPoint;
    }

    public void setLowerPoint(int lowerPoint) {
        this.lowerPoint = lowerPoint;
    }

    public int getWHSum() {
        return lHSum;
    }

    public void setWHSum(int lHSum) {
        this.lHSum = lHSum;
    }

    public int getTotalNOfPositions() {
        return totalNOfPositions;
    }

    public void setTotalNOfPositions(int totalNOfPositions) {
        this.totalNOfPositions = totalNOfPositions;
    }

    public int getTotalNOfBadPositions() {
        return totalNOfBadPositions;
    }

    public void setTotalNOfBadPositions(int totalNOfBadPositions) {
        this.totalNOfBadPositions = totalNOfBadPositions;
    }

    public long getSittingStartTime() {
        return sittingStartTime;
    }

    public void setSittingStartTime(long sittingStartTime) {
        this.sittingStartTime = sittingStartTime;
    }

    public void incNOfEmptyPhotosInARow() {
        this.nOfEmptyPhotosInARow++;
    }

    public int getNOfEmptyPhotosInARow() {
        return nOfEmptyPhotosInARow;
    }

    public void setNOfEmptyPhotosInARow(int nOfEmptyPhotosInARow) {
        this.nOfEmptyPhotosInARow = nOfEmptyPhotosInARow;
    }

    public long getStartOfFollowing() {
        return startOfFollowing;
    }

    public void setStartOfFollowing(long startOfFollowing) {
        this.startOfFollowing = startOfFollowing;
    }


    @Override
    public String toString() {
        return "FPInfo{" +
                "lowerPoint=" + lowerPoint +
                ", lHSum=" + lHSum +
                ", nOfBadPositionsInARow=" + nOfBadPositionsInARow +
                ", totalNOfPositions=" + totalNOfPositions +
                ", totalNOfBadPositions=" + totalNOfBadPositions +
                ", sittingStartTime=" + sittingStartTime +
                ", nOfEmptyPhotosInARow=" + nOfEmptyPhotosInARow +
                ", lastTimeActivity=" + lastTimeActivity +
                ", startOfFollowing=" + startOfFollowing +
                '}';
    }
}
