package com.winterchen.service.impl;

import com.winterchen.dao.DonationDao;
import com.winterchen.model.Donation;
import com.winterchen.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:56 2019/2/20
 * @Modefied By:
 */
@Service(value = "DonationService")
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationDao donationDao;

    @Override
    public int donationKeep(int userId, int donProperty, String donItem, int donAmount) {
        return donationDao.donationKeep(userId, donProperty, donItem, donAmount);
    }

    @Override
    public Donation donationSelectByuserId(int userId) {
        return donationDao.donationSelectByuserId(userId);
    }

    @Override
    public int donationUpdateByuserId(int userId, int donProperty, String donItem, int donAmount) {
        return donationDao.donationUpdateByuserId(userId, donProperty, donItem, donAmount);
    }

    @Override
    public int findAmountLastMonth() {
        return donationDao.findAmountLastMonth();
    }

    @Override
    public int findAmountTotal() {
        return donationDao.findAmountTotal();
    }
}
