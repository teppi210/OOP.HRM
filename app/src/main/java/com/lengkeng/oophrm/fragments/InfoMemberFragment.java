package com.lengkeng.oophrm.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengkeng.oophrm.MemberActivity;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.http.HttpGetEmployeeById;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.Manager;
import com.lengkeng.oophrm.ultis.DialogEdit;

import java.util.concurrent.ExecutionException;

/**
 * Created by Le Vinh Thien on 4/9/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class InfoMemberFragment extends Fragment {
    View view;
    Employee employee;

    public static InfoMemberFragment newInstance(Employee e) {
        InfoMemberFragment newInfoMemberFragment = new InfoMemberFragment();
        newInfoMemberFragment.employee = e;
        return newInfoMemberFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_info, container, false);

        Object e =null;
        try {
            e = new HttpGetEmployeeById(this.employee.getId()).execute().get();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        if (e instanceof Manager) Log.e("e", "Manager");
        else Log.e("e", "Employee");


        TextView tvName = (TextView) view.findViewById(R.id.name);
        TextView tvSex = (TextView) view.findViewById(R.id.sex);
        TextView tvIdnv = (TextView) view.findViewById(R.id.idnv);
        TextView tvGroup = (TextView) view.findViewById(R.id.group);
        TextView tvPosition = (TextView) view.findViewById(R.id.position);
        ImageView img = (ImageView) view.findViewById(R.id.img_user);
        TextView tvDateOfbirth = (TextView) view.findViewById(R.id.dateofbirth);
        TextView tvBonus = (TextView) view.findViewById(R.id.bonus);
        TextView tvBonus2 = (TextView) view.findViewById(R.id.bonus2);
        TextView tvSalary = (TextView) view.findViewById(R.id.salary);

        if (e instanceof Manager){
            tvName.setText(((Manager) e).getName());
            tvSex.setText(((Manager) e).getSex());
            tvDateOfbirth.setText(((Manager) e).getDateofbirth());
            tvIdnv.setText(((Manager) e).getId()+"");
            tvGroup.setText(((Manager) e).getGroup());
            tvPosition.setText(((Manager) e).getPosition());
            tvBonus.setVisibility(View.VISIBLE);
            tvBonus2.setVisibility(View.VISIBLE);
            tvBonus2.setText(((Manager) e).getBonus()+"");
            tvSalary.setText(((Manager) e).getSalary()+"");
            if(((Manager) e).getSex().equals("Nam"))
                img.setImageResource(R.drawable.user_boy);
            else img.setImageResource(R.drawable.user_girl);
        }
        else if(e instanceof Employee){
            tvName.setText(((Employee) e).getName());
            tvSex.setText(((Employee) e).getSex());
            tvDateOfbirth.setText(((Employee) e).getDateofbirth());
            tvIdnv.setText(((Employee) e).getId()+"");
            tvGroup.setText(((Employee) e).getGroup());
            tvPosition.setText(((Employee) e).getPosition());
            tvBonus.setVisibility(View.GONE);
            tvBonus2.setVisibility(View.GONE);
            tvSalary.setText(((Employee) e).getSalary());
            if(((Employee) e).getSex().equals("Nam"))
                img.setImageResource(R.drawable.user_boy);
            else img.setImageResource(R.drawable.user_girl);
        }

        final Object finalE = e;
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v4.app.DialogFragment dialogFragment= new DialogEdit();
                DialogEdit dialogEdit;
                if(finalE instanceof Manager) {
                    dialogEdit = new DialogEdit();
                    dialogEdit.setManager((Manager)finalE);
                    dialogEdit.show(getFragmentManager(),"abc");

                }else if(finalE instanceof Employee) {
                    dialogEdit = new DialogEdit();
                    dialogEdit.setEmployee((Employee)finalE);
                    Log.e("emp2", "em2" + (dialogEdit.getEmployee()).getName());
                    dialogEdit.show(getFragmentManager(),"abc");
                }
            }
        });

        return view;
    }
}
