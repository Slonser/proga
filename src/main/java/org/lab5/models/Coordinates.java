package org.lab5.models;

import com.opencsv.bean.CsvBindByName;
import org.lab5.annotations.DataModel;
import org.lab5.annotations.Max;
import org.lab5.annotations.NotNull;
import org.lab5.annotations.Prompt;

import java.io.Serializable;

@DataModel
public class Coordinates implements Cloneable, Serializable {
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

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
