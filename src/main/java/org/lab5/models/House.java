package org.lab5.models;

import com.opencsv.bean.CsvBindByName;
import org.lab5.annotations.*;

import java.io.Serializable;

@DataModel
public class House extends Model implements Cloneable, Serializable {

    @CsvBindByName(column = "name")
    @CustomName(name = "Название дома")
    @Prompt(description = "Введите имя дома: ")
    @NotNull()
    private String name; //Поле может быть null
    @CsvBindByName(column = "year")
    @CustomName(name = "Год постройки")
    @Prompt(description = "Введите год постройки дома: ")
    @Min()
    @Max(max = 616)
    private Long year; //Поле может быть null, Значение поля должно быть больше 0
    @CsvBindByName(column = "numberOfFloors")
    @CustomName(name = "Количество этажей")
    @Prompt(description = "Введите количество этажей дома: ")
    @Min()
    private Long numberOfFloors; //Значение поля должно быть больше 0
    @CsvBindByName(column = "numberOfFlatsOnFloor")
    @CustomName(name = "Количество квартир")
    @Prompt(description = "Введите количество квартир на этаже: ")
    @Min()
    private long numberOfFlatsOnFloor; //Значение поля должно быть больше 0

    @CsvBindByName(column = "numberOfLifts")
    @CustomName(name = "Количество лифтов")
    @Prompt(description = "Введите количество лифтов на этаже: ")
    @Min()
    private int numberOfLifts; //Значение поля должно быть больше 0

    public House() {
    }

    @Override
    protected void initializeSetterMap() {
        registerSetter("name", (value) -> setName((String) value));
        registerSetter("year", (value) -> setYear((Long) value));
        registerSetter("numberOfFloors", (value) -> setNumberOfFloors((Long) value));
        registerSetter("numberOfFlatsOnFloor", (value) -> setNumberOfFlatsOnFloor((Long) value));
        registerSetter("numberOfLifts", (value) -> setNumberOfLifts((Integer) value));

    }

    public House(String name, Long year, Long numberOfFloors, int numberOfFlatsOnFloor, int numberOfLifts) {
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
        this.numberOfLifts = numberOfLifts;
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public long getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Long numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public Long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public void setNumberOfFlatsOnFloor(int numberOfFlatsOnFloor) {
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public void setNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) {
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public void setNumberOfLifts(int numberOfLifts) {
        this.numberOfLifts = numberOfLifts;
    }

    @Override
    public String toString() {
        return
                 name +
                "\nдом -> год:" + year +
                "\nдом -> количество этажей:" + numberOfFloors +
                "\nдом -> количество квартир на этаже:" + numberOfFlatsOnFloor +
                "\nдом -> количество лифтов:" + numberOfLifts;
    }

    public int getNumberOfLifts() {
        return numberOfLifts;
    }
}