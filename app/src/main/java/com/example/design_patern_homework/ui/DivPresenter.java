package com.example.design_patern_homework.ui;

import com.example.design_patern_homework.pojo.NumberModel;

public class DivPresenter {

    private DivView view;

    public DivPresenter(DivView view) {
        this.view = view;
    }

    private NumberModel getDatabaseModel(){
        DataBase dataBase = new DataBase();
        return dataBase.getNumbers();
    }

    public void getDivRes(){
        NumberModel model = getDatabaseModel();
        int firstNum = model.getFirstNum();
        int secondNum = model.getSecondNum();
        int div = firstNum/secondNum;
        view.onGetDivRes(div+"");
    }
}
