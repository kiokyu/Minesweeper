package com.company.logic;

public class Matrix {
    private Box[][] matrix;

    //Конструктор
    Matrix(Box defBox){
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];// Размеры матрицы

        //Цикл заполнения матрицы значениями
        for (Coord coord : Ranges.getAllCoords()) {
            matrix[coord.x][coord.y] = defBox;
        }
    }

    // Геттер(возвращает значение из определенной координаты матрицы)
    Box get (Coord coord){

        if (Ranges.inRange(coord)) {// Цикл проверяещий, назодимся ли мы в пределах массива
            return matrix[coord.x][coord.y];
        }else
            return null;
    }

    // Сеттер (Устанавливает нужные значения в указанную клетку
    void set (Coord coord, Box box){
        if(Ranges.inRange(coord)) {// Цикл проверяещий, назодимся ли мы в пределах массива (
            matrix[coord.x][coord.y] = box;
        }
    }
}
