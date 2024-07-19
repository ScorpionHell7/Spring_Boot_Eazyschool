package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Holiday;
import com.eazybytes.eazyschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidaysController {
    @Autowired
    HolidayRepository holidayRepository;
    @GetMapping("/holidays/{display}")
    public String diaplayHolidays(@PathVariable String display, Model model) {
        if(null!=display && display.equals("all")){
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        }
        else if(display.equals("federal")){
            model.addAttribute("festival", false);
            model.addAttribute("federal", true);
        }
        else if(display.equals("festival")){
            model.addAttribute("festival", true);
            model.addAttribute("federal", false);
        }

        Iterable<Holiday> holidays = holidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(),false)
                .collect(Collectors.toList());
        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type type : types){
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).
                            collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
