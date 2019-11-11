package com.wis.pojo.po;

public class Word {

    private String english;
    private String chinese;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return "Word{" +
                "english='" + english + '\'' +
                ", chinese='" + chinese + '\'' +
                '}';
    }
}
