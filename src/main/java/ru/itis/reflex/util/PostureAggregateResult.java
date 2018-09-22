package ru.itis.reflex.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostureAggregateResult {

    private double smoothNum;
    private double flexNum;
    private Date date;
}
