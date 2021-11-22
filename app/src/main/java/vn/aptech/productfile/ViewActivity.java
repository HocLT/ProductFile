package vn.aptech.productfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.io.ObjectInputStream;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private RecyclerView rvProduct;

    private List<ProductDto> products;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        rvProduct = findViewById(R.id.rvProduct);

        // Xử lý lưu dữ liệu vào file
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

        if (products == null) {
            // có thể hiển thị dialog thông báo
            onBackPressed();
        } else {
            Log.d("ViewActivity", "------ Product Size: " + products.size());
            // tạo adapter từ products
            adapter = new ProductAdapter(this, products);
            rvProduct.setAdapter(adapter);  // gắn adapter vào RecyclerView
            // cần thiết lập layout cho RecyclerView
            rvProduct.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}