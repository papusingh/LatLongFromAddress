package provider.androidbuffer.com.latlongaddress;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText sourceAdd, destAdd;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceAdd = findViewById(R.id.etSourceAdd);
        destAdd = findViewById(R.id.etDestAdd);
        btn = findViewById(R.id.btn);
        getLatLongFromAddress();
    }

    private void getLatLongFromAddress(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add1 = sourceAdd.getText().toString();
                String add2 = destAdd.getText().toString();
                getData(add1,add2);
            }
        });
    }

    public void getData(String address1, String address2){
        Geocoder geocoder = new Geocoder(this);
        List<Address> sourceAddresses;
        List<Address> destinationAddresses;
        try {
            sourceAddresses = geocoder.getFromLocationName(address1,5);
            destinationAddresses = geocoder.getFromLocationName(address2,5);
            if (sourceAddresses == null){
                return;
            }
            Address location1 = sourceAddresses.get(0);
            Double lat1 = location1.getLatitude();
            Double longitude1 = location1.getLongitude();

            if (destinationAddresses == null){
                return;
            }
            Address location2 = destinationAddresses.get(0);
            Double lat2 = location2.getLatitude();
            Double longitude2 = location2.getLongitude();

            Log.d("TAG lat : ",lat1+" "+lat2);
            Log.d("TAG long : ",longitude1+" "+longitude2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
