package com.sparta.academy.mfix_mongodb_api.model.entity.theater;

//@Document(collection = "addresses")
public class Address {

//    @Field
    private String zipcode;

//    @Field
    private String city;

//    @Field
    private String street1;

//    @Field
    private String state;

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet1() {
        return street1;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return
                "Address{" +
                        "zipcode = '" + zipcode + '\'' +
                        ",city = '" + city + '\'' +
                        ",street1 = '" + street1 + '\'' +
                        ",state = '" + state + '\'' +
                        "}";
    }
}