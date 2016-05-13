package com.echo.eui.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.echo.eui.R;
import com.echo.eui.dao.PersonService;
import com.echo.eui.entity.Person;

import java.util.List;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText id, name, pass;
    private Button add, update, delete, multiDelete, find, multiFind, page, select;
    private TextView tv;
    private PersonService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_add:
                save();
                break;
            case R.id.data_update:
                update();
                break;
            case R.id.data_delete:
                delete();
                break;
            case R.id.data_multiDelete:
                multiDelete();
                break;
            case R.id.data_find:
                find();
                break;
            case R.id.data_multiFind:
                multiFind();
                break;
            case R.id.data_page:
                page();
                break;
            case R.id.data_select:
                select();
                break;
            default:
                break;
        }
    }

    private void page() {
        List<Person> list = service.pageFind(2, 5);
        tv.setText(list.toString());
    }

    private void multiDelete() {
        Integer[] ids = {1, 2};
        service.deleteByIds(ids);
        tv.setText("multiDelete successful");
    }

    private void delete() {
        service.delete(Integer.parseInt(id.getText().toString()));
        tv.setText("delete successful");
    }

    private void multiFind() {
        Integer[] ids = {1, 2};
        List<Person> list = service.findByIds(ids);
        tv.setText(list.toString());
    }

    private void select() {
        List<Person> list = service.select();
        tv.setText(list.toString());
    }

    private void update() {
        Person person = new Person(Integer.parseInt(id.getText().toString()), name.getText().toString(), pass.getText().toString());
        service.update(person);
        tv.setText("update successful");
    }

    private void find() {
        Person person = service.find(Integer.parseInt(id.getText().toString()));
        tv.setText(person.toString());
        System.out.println(person.toString());
    }

    private void save() {
        Person person = new Person(Integer.parseInt(id.getText().toString()), name.getText().toString(), pass.getText().toString());
        service.save(person);
        tv.setText("save successful");
    }

    private void initView() {
        id = (EditText) findViewById(R.id.data_id);
        name = (EditText) findViewById(R.id.data_name);
        pass = (EditText) findViewById(R.id.data_pass);

        add = (Button) findViewById(R.id.data_add);
        update = (Button) findViewById(R.id.data_update);
        delete = (Button) findViewById(R.id.data_delete);
        multiDelete = (Button) findViewById(R.id.data_multiDelete);
        find = (Button) findViewById(R.id.data_find);
        multiFind = (Button) findViewById(R.id.data_multiFind);
        page = (Button) findViewById(R.id.data_page);
        select = (Button) findViewById(R.id.data_select);

        tv = (TextView) findViewById(R.id.data_tv);

        add.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        multiDelete.setOnClickListener(this);
        find.setOnClickListener(this);
        multiFind.setOnClickListener(this);
        page.setOnClickListener(this);
        select.setOnClickListener(this);

        service = new PersonService(this, 3);
    }
}
