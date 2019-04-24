package com.winterchen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: liuzipan
 * @Description
 * @Date :15:25 2019/2/23
 * @Modefied By:
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @RequestMapping("/gallery")
    public String gallery(){
        return "/gallery";
    }

    @RequestMapping("/gallery_new")
    public String galleryNew(){
        return "/gallery_new";
    }

    @RequestMapping("/HomePage")
    public String HomePage(){
        return "/HomePage";
    }

    @RequestMapping("/Register")
    public String Register(){
        return "Register";
    }


    @RequestMapping("/forgetPassword")
    public String forgetPassword(){
        return "ForgetPassword";
    }

    @RequestMapping("/CharacterStyle")
    public String CharacterStyle(){
        return "CharacterStyle";
    }

    @RequestMapping("/zekeLogin")
        public String zekeLogin(){
            return "zekeLogin";
        }

    @RequestMapping("/single")
    public String single(){
        return "single";
    }

    @RequestMapping("/news")
    public String news(){
        return "news";
    }

    @RequestMapping("/Company")
    public String Company(){
        return "Company";
    }

    @RequestMapping("/news_detail")
    public String news_detail(){
        return "news_detail";
    }

    @RequestMapping("/announcement_detail")
    public String announcement_detail(){
        return "announcement_detail";
    }


    @RequestMapping("/companyUser")
    public String companyUser(){
        return "companyUser";
    }

    @RequestMapping("/companyUserDetail")
    public String companyUserDetail(){
        return "companyUserDetail";
    }

    @RequestMapping("/service")
    public String service(){
        return "service";
    }

    @RequestMapping("/Service")
    public String Service(){
        return "Service";
    }

    @RequestMapping("/Service_detail")
    public String Service_detail(){
        return "Service_detail";
    }

    @RequestMapping("/Service_detail2")
    public String Service_detail2(){
        return "Service_detail2";
    }


    @RequestMapping("/Service_souvenir")
    public String Service_souvenir(){
        return "Service_souvenir";
    }

    @RequestMapping("/Service_SchoolAbility")
    public String Service_SchoolAbility(){
        return "Service_SchoolAbility";
    }

    @RequestMapping("/Service_SchoolCard")
    public String Service_SchoolCard(){
        return "Service_SchoolCard";
    }

    @RequestMapping("/Service_returnSchool")
    public String Service_returnSchool(){
        return "Service_returnSchool";
    }

    @RequestMapping("/Service_hotel")
    public String Service_hotel(){
        return "Service_hotel";
    }

    @RequestMapping("/Service_list_detail")
    public String Service_list_detail(){
        return "Service_list_detail";
    }

    @RequestMapping("/admin-user")
    public String adminUser(){
        return "admin-user";
    }

    @RequestMapping("/admin-table")
    public String adminTable(){
        return "admin-table";
    }

    @RequestMapping("/admin-log")
    public String adminLog(){
        return "admin-log";
    }

    @RequestMapping("/admin-index")
    public String adminIndex(){
        return "admin-index";
    }

    @RequestMapping("/admin-help")
    public String adminHelp(){
        return "admin-help";
    }

    @RequestMapping("/admin-gallery")
    public String adminGallery(){
        return "admin-gallery";
    }

    @RequestMapping("/admin-form")
    public String adminForm(){
        return "admin-form";
    }

    @RequestMapping("/sysindex")
    public String sysindex(){
        return "sysindex";
    }

    @RequestMapping("/systables")
    public String systables(){
        return "systables";
    }

    @RequestMapping("/sysprofile")
    public String sysprofile(){
        return "sysprofile";
    }

    @RequestMapping("/syslogin")
    public String syslogin(){
        return "syslogin";
    }

    @RequestMapping("/sysgallery")
    public String sysgallery(){
        return "sysgallery";
    }

    @RequestMapping("/sysform-layouts")
    public String sysformLayouts(){
        return "sysform-layouts";
    }

    @RequestMapping("/syschart")
    public String syschart(){
        return "syschart";
    }


    @RequestMapping("/sysbasic-form")
    public String sysbasicForm(){
        return "sysbasic-form";
    }

    @RequestMapping("/sysregister")
    public String sysregister(){
        return "sysregister";
    }


    @RequestMapping("/PersonalInfo")
    public String PersonalInfo(){
        return "PersonalInfo";
    }

    @RequestMapping("/PersonalDonation")
    public String PersonalDonation(){
        return "PersonalDonation";
    }

    @RequestMapping("/PersonalStroke")
    public String PersonalStroke(){
        return "PersonalStroke";
    }

    @RequestMapping("/sysUserInfo")
    public String sysUserInfo(){
        return "sysUserInfo";
    }



}
