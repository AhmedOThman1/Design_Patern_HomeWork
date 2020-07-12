package com.example.design_patern_homework.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.design_patern_homework.R;
import com.example.design_patern_homework.pojo.NumberModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Button plus_button;
    TextView plus_result_textView;
    @BindView(R.id.div_button)
    Button div_button;
    @BindView(R.id.div_result_textView)
    TextView div_result_textview;
    @BindView(R.id.mul_button)
    Button mulButton;
    @BindView(R.id.mul_result_textView)
    TextView mulResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ////////////    MVC     /////////////////////////

        plus_button = findViewById(R.id.plus_button);
        plus_result_textView = findViewById(R.id.plus_result_textView);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MVC

                String sum = getSum();
                plus_result_textView.setText(sum);
            }
        });

        ////////////     MVP     /////////////////////////

        final DivPresenter presenter = new DivPresenter(new DivView() {
            @Override
            public void onGetDivRes(String divRes) {
                div_result_textview.setText(divRes);
            }
        });

        div_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MVP
                presenter.getDivRes();
            }
        });

        ////////////     MVVM     /////////////////////////

        MulViewModel viewModel = new ViewModelProvider(this).get(MulViewModel.class);

        viewModel.mulMutableLiveData.observe(this, s -> {
            //MVVM   get data from the MutableLiveData and pass it to the view
            mulResultTextView.setText(s);
        });

        mulButton.setOnClickListener(v -> {
            //MVVM  to set the MutableLifeData
            viewModel.getMulRes();
        });

        //////////////////   end of android architecture pattern   /////////////////////////////
    }

    private String getSum() {
        // MVC
        NumberModel model = getDatabaseModel();
        int firstNum = model.getFirstNum();
        int secondNum = model.getSecondNum();
        int sum = firstNum + secondNum;
        return sum + "";
    }

    NumberModel getDatabaseModel() {
        // MVC
        DataBase dataBase = new DataBase();
        return dataBase.getNumbers();
    }
}
