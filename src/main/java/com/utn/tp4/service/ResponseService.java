package com.utn.tp4.service;

import com.utn.tp4.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.tp4.repository.DaoResponse;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private DaoResponse dao;

    @Transactional
    public boolean addResponse(Response response) {
        System.out.println("bwsr: "+response.getBrowserName());
        System.out.println("so: "+response.getSoName());
        try {
            dao.save(response);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<?> getResponses(){
        return dao.getResponses();
    }

    public List<?> getPopularBrowser(){
        return dao.popularBrowser();
    }

    public List<?> getPopularSo(){
        return dao.popularSo();
    }

    public List<?> getPopularCombination(){
        return dao.popularCombination();
    }
}
