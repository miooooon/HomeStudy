package com.example.demo.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm  implements Serializable {
    


    private static final long serialVersionUID = 1L;
    
    
    @Pattern(regexp = "A|B|C|D|E")
    private String fradio;
    
    @Pattern(regexp = "A|B|C|D|E")
    private String fradio2;

    public String getFradio() {
        return fradio;
    }
    
    public void setFradio(String fradio) {
        this.fradio = fradio;
    }
    
    public String getFradio2() {
        return fradio2;
    }
    
    public void setFradio2(String fradio2) {
        this.fradio = fradio2;
    }

//        @NotBlank(message="選択してね")
//        private String category;
//
//        @NotBlank(message="何か入力してね")
//        @Length(max=1000)
//        private String content;
//
//        public Map<String, String> getCategoryList() {
//        Map<String, String> categoryMap = new LinkedHashMap<String, String>();
//            categoryMap.put("1", "カテゴリー１");
//            categoryMap.put("2", "カテゴリー２");
//            categoryMap.put("3", "カテゴリー３");
//            return categoryMap;
//        }
    
}
