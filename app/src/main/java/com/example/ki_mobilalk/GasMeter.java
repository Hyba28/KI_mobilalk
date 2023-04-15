package com.example.ki_mobilalk;

import java.time.LocalDate;

public class GasMeter {
    private final Integer serialNumber;
    private final LocalDate validThrough;
    private Integer value;
    private Integer postCode;
    private String address;

    public GasMeter(Integer serialNumber, LocalDate validThrough, Integer value) {
        this.serialNumber = serialNumber;
        this.validThrough = validThrough;
        this.value = value;
    }

    public GasMeter(Integer serialNumber, LocalDate validThrough) {
        this.serialNumber = serialNumber;
        this.validThrough = validThrough;
        this.value = 0;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public LocalDate getValidThrough() {
        return validThrough;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) throws Exception {
        if(value < this.value){
            throw new Exception("Nem tekerheted hatra az orat!");
        }else {
            this.value = value;
        }

    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
