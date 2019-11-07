package maa.myfishing.service.models;

import org.springframework.stereotype.Service;

@Service
public class OvertightBindingModel {
    private String overnight;

    public OvertightBindingModel() {
    }

    public String getOvernight() {
        return overnight;
    }

    public void setOvernight(String overnight) {
        this.overnight = overnight;
    }
}
