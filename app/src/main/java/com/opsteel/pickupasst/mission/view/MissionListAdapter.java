package com.opsteel.pickupasst.mission.view;
/**
 * 文 件 名: MissionListAdapter
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：任务列表adapter
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.jakewharton.rxbinding2.view.RxView;
import com.opsteel.pickupasst.R;
import com.opsteel.pickupasst.mission.model.bean.Green;
import com.opsteel.pickupasst.mission.model.bean.StoreGreen;
import com.opsteel.pickupasst.mission.model.bean.WcGreenList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by C560 on 2017/10/24.
 */

public class MissionListAdapter extends RecyclerView.Adapter {
    private List<Object> mList;
    private Context context;
    private int ITEM_TITLE = 1, ITEM_HEADER = 2, ITEM_CONTENT = 3;

    public MissionListAdapter(Context content, List<Object> mList) {
        this.mList = mList;
        this.context = content;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_mission_header, parent, false);
            return new ViewHolderHeader(view);
        } else if (viewType == ITEM_CONTENT) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_mission_content, parent, false);
            return new ViewHolderContent(view);
        } else if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_mission_title, parent, false);
            return new ViewHolderTitle(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderHeader) {
            StoreGreen storeGreen = (StoreGreen) mList.get(position);
            ((ViewHolderHeader) holder).storenameText.setText(storeGreen.getStorename());
            ((ViewHolderHeader) holder).contentText.setText(storeGreen.getBillnum() + "");
        } else if (holder instanceof ViewHolderContent) {
            ViewHolderContent content = (ViewHolderContent)holder;
            final Green green = (Green) mList.get(position);
            content.idText.setText(green.getBlid());
            content.dateText.setText(green.getBldate());
            if (green.getGreenstatus() == 1) {
                content.statusText.setText("可提货");
                content.statusText.setTextColor(context.getResources().getColor(R.color.mainColor));
                content.qrBtn.setVisibility(View.VISIBLE);
                RxView.clicks(content.qrBtn)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                QRDialogFragment.newInstance(green.getBlid()).show(((Activity) context).getFragmentManager(), "");
                            }
                        });
            } else {
                content.statusText.setText("未缴款，提货前请先缴款");
                content.statusText.setTextColor(context.getResources().getColor(R.color.secondColor));
                content.qrBtn.setVisibility(View.GONE);
            }
            content.line.setVisibility(green.isLastone() ? View.VISIBLE : View.GONE);
            content.weightText.setText(green.getWeight() + "");
            content.cuText.setText(green.getCufullname());

            RxView.clicks(content.constraintLayout)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            MissionDetailActivity.openActivity(context);
                        }
                    });

        } else if (holder instanceof ViewHolderTitle) {
            ViewHolderTitle title = (ViewHolderTitle)holder;
            final WcGreenList wcGreenList = (WcGreenList) mList.get(position);
            title.shownameText.setText(wcGreenList.getWcshowname());
            RxView.clicks(title.dialBtn)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + wcGreenList.getTelephone());
                            intent.setData(data);
                            context.startActivity(intent);
                        }
                    });

            RxView.clicks(title.locationBtn)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Toast.makeText(context, "定位功能还未实现", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = mList.get(position);
        if (object instanceof WcGreenList) {
            return ITEM_TITLE;
        } else if (object instanceof StoreGreen) {
            return ITEM_HEADER;
        } else if (object instanceof Green) {
            return ITEM_CONTENT;
        }
        return ITEM_CONTENT;
    }

    class ViewHolderContent extends RecyclerView.ViewHolder {
        private TextView idText;
        private TextView dateText;
        private TextView cuText;
        private TextView weightText;
        private TextView statusText;
        private Button qrBtn;
        private View line;
        private ConstraintLayout constraintLayout;

        public ViewHolderContent(View view) {
            super(view);
            idText = view.findViewById(R.id.idValueText);
            dateText = view.findViewById(R.id.dateValueText);
            cuText = view.findViewById(R.id.cunameValueText);
            weightText = view.findViewById(R.id.weightValueText);
            statusText = view.findViewById(R.id.statusValueText);
            qrBtn = view.findViewById(R.id.qrBtn);
            line = view.findViewById(R.id.line1);
            constraintLayout = view.findViewById(R.id.content_layout);
        }
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {
        private TextView storenameText;
        private TextView contentText;

        public ViewHolderHeader(View view) {
            super(view);
            storenameText = view.findViewById(R.id.storename);
            contentText = view.findViewById(R.id.contentText);
        }
    }

    class ViewHolderTitle extends RecyclerView.ViewHolder {
        private TextView shownameText;
        private Button locationBtn;
        private Button dialBtn;

        public ViewHolderTitle(View view) {
            super(view);
            shownameText = view.findViewById(R.id.shownameText);
            locationBtn = view.findViewById(R.id.locationBtn);
            dialBtn = view.findViewById(R.id.dialBtn);
        }
    }

}
