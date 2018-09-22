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
public class AggregateResult {

    private double morningValue;
    private double eveningValue;
    private Date date;
}
