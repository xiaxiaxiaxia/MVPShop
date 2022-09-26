package run.demo01.mvpshop.bean;

import java.util.List;

public class Tabs {
    private String name;
    private List<String> pictures;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Tabs{" +
                "name='" + name + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
