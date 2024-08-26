package com.example.makelayout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class workout_fix_item extends AppCompatActivity {

    private ImageButton fixEditBtn;
    private TextView workoutNameTextView, setAndRepTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // XML 레이아웃 파일 연결

        // 뷰 초기화
        fixEditBtn = findViewById(R.id.fix_editBtn);
        workoutNameTextView = findViewById(R.id.workout_name_textview);  // 운동명 텍스트뷰
        setAndRepTextView = findViewById(R.id.set_and_rep_textview);  // 세트 수 x 반복 횟수 텍스트뷰

        // 수정 버튼 클릭 리스너
        fixEditBtn.setOnClickListener(v -> showEditPopup());
    }

    private void showEditPopup() {
        // 팝업 창의 레이아웃을 인플레이트
        LayoutInflater inflater = LayoutInflater.from(this);
        View popupView = inflater.inflate(R.layout.popup_edit_workout, null);

        // 팝업 창 내의 EditText와 Button 참조
        EditText setCountEditText = popupView.findViewById(R.id.edit_set_count);
        EditText repetitionCountEditText = popupView.findViewById(R.id.edit_repetition_count);
        Button saveButton = popupView.findViewById(R.id.save_button);

        // 팝업 창을 다이얼로그로 표시
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);
        AlertDialog dialog = builder.create();

        // 저장 버튼 클릭 리스너
        saveButton.setOnClickListener(v -> {
            String setCount = setCountEditText.getText().toString();
            String repetitionCount = repetitionCountEditText.getText().toString();

            if (!setCount.isEmpty() && !repetitionCount.isEmpty()) {
                // 세트 수와 반복 횟수를 TextView에 표시
                setAndRepTextView.setText(setCount + " x " + repetitionCount);
                dialog.dismiss();  // 팝업 닫기
            } else {
                Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();  // 팝업 표시
    }
}
