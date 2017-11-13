package com.opsteel.pickupasst.mission.view;
/**
 * 文 件 名: MissionListActivity
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：任务列表
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.opsteel.pickupasst.R;
import com.opsteel.pickupasst.base.BaseActivity;
import com.opsteel.pickupasst.mission.presenter.MissionListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MissionListActivity extends BaseActivity implements IMissionListView {

    private RecyclerView recyclerView;
    private MissionListAdapter mAdapter;
    private SmartRefreshLayout smartRefreshLayout;
    private List<Object> objectList;
    private MissionListPresenter missionListPresenter;
    private TextView emptyView;
    public static void openActivity(Context context) {
        Intent intent = new Intent(context, MissionListActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mission_list;
    }

    @Override
    protected void initView() {

        initTitle(R.string.title_mission);
        hideBack();
        recyclerView = findViewById(R.id.recyclerView);
        smartRefreshLayout = findViewById(R.id.refreshLayout);
        //设置 Header 为 Material样式
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this).setSpinnerStyle(SpinnerStyle.Scale));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyView = findViewById(R.id.empty_view);
    }

    private void showEmptyView() {
        if (objectList.isEmpty()) {
            smartRefreshLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            smartRefreshLayout.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartRefreshLayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smartRefreshLayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    protected void initData() {
        objectList = new ArrayList<>();
        mAdapter = new MissionListAdapter(this, objectList);
        recyclerView.setAdapter(mAdapter);
        showEmptyView();
        missionListPresenter = new MissionListPresenter(this);
        missionListPresenter.getMissionList();
    }

    @Override
    public void getMissionList(List<Object> list) {
        objectList.clear();
        objectList.addAll(list);
        mAdapter.notifyDataSetChanged();
        showEmptyView();
    }
}
