package org.lab5.models;

import com.opencsv.bean.CsvBindByName;
import org.lab5.annotations.DataModel;
import org.lab5.annotations.Max;
import org.lab5.annotations.NotNull;
import org.lab5.annotations.Prompt;

import java.io.Serializable;

@DataModel
public class Coordinates extends Model implements Cloneable, Serializable   {
    @CsvBindByName(column = "x")
    @Prompt(description = "Введите координату x: ")
    @NotNull()
    @Max(max = 2)
    private int x; //Поле не может быть null
    @CsvBindByName(column = "y")
    @Prompt(description = "Введите координату y: ")
    @NotNull()
    private Float y;


    public Coordinates() {
    }

    @Override
    protected void initializeSetterMap() {
        registerSetter("x",(value) -> setX((Integer) value));
        registerSetter("y",(value) -> setY((Float) value));
    }

    public Coordinates(int x, Float y) {
        this.x = x;
        this.y = y;
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "координаты -> x: "+ x + "\nкоординты -> y: " + y;
    }
}
