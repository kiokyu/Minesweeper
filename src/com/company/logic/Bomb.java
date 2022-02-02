package com.company.logic;

// Класс отвечающий за бомбы
class Bomb {
    private Matrix bombMab;
    private int totalBombs;

    Bomb(int totalBombs) {// Конструктор
        this.totalBombs = totalBombs;

        fixBombsCount();
    }

    //Метод, ставящий бомбы на матрице
    void start() {
        bombMab = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    Box get(Coord coord) {
        return bombMab.get(coord);
    }

    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    //Метод, создающий бомбы
    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMab.get(coord)) {
                continue;
            }
            bombMab.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMab.get(around)) {
                bombMab.set(around, bombMab.get(around).getNextNumberBox());
            }
        }
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
