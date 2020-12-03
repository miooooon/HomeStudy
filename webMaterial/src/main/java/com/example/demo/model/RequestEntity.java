package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.demo.domain.ItemTag;
import com.example.demo.domain.ItemType;

@Component
public class RequestEntity implements Serializable {
    
        private static final long serialVersionUID = 1L;
        /** 種類 */
        private Integer itemType;
        /** タグ */
        private Integer itemTag;
        /** 登録日 */
        private LocalDateTime registDate;

        public RequestEntity() {
            this.itemType = null;
            this.itemTag = null;
            this.registDate = null;
        }

        public Integer getItemType() {
            return itemType;
        }

        public void setItemType(Integer integer) {
            this.itemType = integer;
        }

        public Integer getItemTag() {
            return itemTag;
        }

        public void setItemTag(Integer itemTag) {
            this.itemTag = itemTag;
            
        }

        public LocalDateTime getRegistDate() {
            return this.registDate;
        }

        
        public void setRegistDate(LocalDateTime registDate) {
            this.registDate = registDate;
        }
}
