package com.iqbuzz.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SeanceService {

    @Autowired
    private Environment env;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

    public List<String> list(){
        int interval = Integer.valueOf(env.getProperty("seance.interval"));
        LocalTime now = LocalTime.now();
        LocalTime localTime = now.minusHours(1).withMinute(0);

        while(localTime.isBefore(now)){
            localTime = localTime.plusMinutes(interval);
        }
        int count = Integer.valueOf(env.getProperty("seance.count"));

        List<String> seances = new ArrayList<>();
        for(int i = 0; i< count; i++){
            localTime = localTime.plusMinutes(interval);
            seances.add(formatter.format(localTime));
        }
        return seances;
    }

}
