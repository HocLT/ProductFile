package vn.aptech.productfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private EditText edId, edName, edPrice;
    private Button btnCreate, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        edId = findViewById(R.id.edId);
        edName = findViewById(R.id.edName);
        edPrice = findViewById(R.id.edPrice);
        btnCreate = findViewById(R.id.btnCreate);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();    // tương đương với việc nhấn nút Back của hdh Android
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // đọc dữ liệu từ các EditText
                String id = edId.getText().toString();
                String name = edName.getText().toString();
                String price = edPrice.getText().toString();

                ProductDto dto = new ProductDto(Integer.parseInt(id),
                        name, Integer.parseInt(price));

                // Xử lý lưu dữ liệu vào file
                List<ProductDto> products = null;
                try {
                    // 1. Đọc dữ liệu cũ từ file
                    ObjectInputStream in = new ObjectInputStream(
                            openFileInput("product.dat"));
                    // đọc dữ liệu từ file, có ép kiểu
                    products = (List<ProductDto>) in.readObject();
                    in.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // kiểm tra nếu không có dữ liệu thì tạo mới
                if (products == null) {
                    products = new ArrayList<>();
                }

                products.add(dto);  // thêm product mới với danh sách

                // lưu lại product list vào file
                try {
                    ObjectOutputStream out = new ObjectOutputStream(
                            openFileOutput("product.dat", Context.MODE_PRIVATE));
                    out.writeObject(products);
                    out.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // xóa các ô nhập liệu
                edId.setText("");
                edName.setText("");
                edPrice.setText("");
                // chuyển focus về ô Id để nhập liệu
                edId.requestFocus();
            }
        });
    }
}