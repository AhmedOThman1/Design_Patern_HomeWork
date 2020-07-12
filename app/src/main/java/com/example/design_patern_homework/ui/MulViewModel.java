package com.example.design_patern_homework.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.design_patern_homework.pojo.NumberModel;

public class MulViewModel extends ViewModel {

    MutableLiveData<String> mulMutableLiveData = new MutableLiveData<>();
    private NumberModel getDatabaseModel(){
        DataBase dataBase = new DataBase();
        return dataBase.getNumbers();
    }

    void getMulRes(){
        NumberModel model = getDatabaseModel();
        int firstNum = model.getFirstNum();
        int secondNum = model.getSecondNum();
        int mulRes = firstNum*secondNum;

        mulMutableLiveData.setValue(""+mulRes);
    }
}
