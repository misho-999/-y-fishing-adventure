package maa.myfishing.service.models;

import org.springframework.stereotype.Service;

@Service
public class OverNightBindingModel {
    private String overnight;

    public OverNightBindingModel() {
    }

    public String getOvernight() {
        return overnight;
    }

    public void setOvernight(String overnight) {
        this.overnight = overnight;
    }
}
