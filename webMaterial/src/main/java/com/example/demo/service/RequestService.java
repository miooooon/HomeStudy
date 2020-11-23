package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ItemEntity;
import com.example.demo.model.RequestEntity;
import com.example.demo.repository.ItemDao;
import com.example.demo.repository.RequestDao;

@Service
public class RequestService {

    /** リクエストDAO */
  @Autowired
  private RequestDao requestDao;
    
    public void registRequest(RequestEntity request) {
            requestDao.registRequest(request);
    }
}
