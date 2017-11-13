package com.opsteel.pickupasst.mission.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.opsteel.pickupasst.R;
import com.opsteel.pickupasst.base.BaseActivity;
import com.opsteel.pickupasst.mission.model.bean.Green;
import com.opsteel.pickupasst.mission.presenter.MissionDetailPresenter;

/**
 * 文 件 名: MissionDetailActivity
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：任务详细
 */

public class MissionDetailActivity extends BaseActivity implements IMissionDetailView{
    private RecyclerView recyclerView;
    private MissionDetailAdapter missionDetailAdapter;
    private MissionDetailPresenter missionDetailPresenter;
    private TextView emptyView;
    private Green green;
    public static void openActivity(Context context) {
        Intent intent = new Intent(context, MissionDetailActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mission_detail;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_mission_detail);
        recyclerView = findViewById(R.id.recyclerView);
        emptyView = findViewById(R.id.empty_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        missionDetailPresenter = new MissionDetailPresenter(this);
        missionDetailPresenter.getDetail();

    }


    private void setHeaderView(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.adapter_mission_detail_header, view, false);
        missionDetailAdapter.setHeaderView(header);
    }

    @Override
    public void getDetail(Green green) {
        this.green = green;
        missionDetailAdapter = new MissionDetailAdapter(this, green);
        recyclerView.setAdapter(missionDetailAdapter);
        setHeaderView(recyclerView);
        showEmptyView();
    }

    private void showEmptyView() {
        if (green.getDtList().isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}
