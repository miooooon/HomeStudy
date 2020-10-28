package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ItemEntity;
import com.example.demo.repository.RegistItemDao;

@Service
public class RegistItemService {

        /** アイテムDAO */
        @Autowired
        private RegistItemDao registItemDao;

        public void registItem(ItemEntity item) {
            registItemDao.registItem(item);
        }
}
