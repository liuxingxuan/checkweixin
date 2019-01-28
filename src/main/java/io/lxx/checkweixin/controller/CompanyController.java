package io.lxx.checkweixin.controller;

import io.lxx.checkweixin.vo.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 打卡地点
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Value("${checkInOut.longitude}")
    private Double logitude;

    @Value("${checkInOut.latitude}")
    private Double latitude;

    //打卡坐标
    @GetMapping("/getCheckInOutLocation")
    public Location getCheckInOutLocation(){
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(logitude);
        return location;
    }
}
