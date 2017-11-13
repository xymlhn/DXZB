package com.opsteel.pickupasst.mission.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.opsteel.pickupasst.R;
import com.opsteel.pickupasst.mission.model.bean.DtList;
import com.opsteel.pickupasst.mission.model.bean.Green;

/**
 * 文 件 名: MissionDetailAdapter
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：任务详细adapter
 */

public class MissionDetailAdapter extends RecyclerView.Adapter {
    private Green green;
    private Context context;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public MissionDetailAdapter(Context content, Green green) {
        this.green = green;
        this.context = content;
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;

        notifyItemInserted(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ListHolder(mHeaderView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mission_detail, parent, false);
        return new ListHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((TextView) mHeaderView.findViewById(R.id.idValueText)).setText(green.getBlid());
            ((TextView) mHeaderView.findViewById(R.id.dateValueText)).setText(green.getBldate());
            ((TextView) mHeaderView.findViewById(R.id.cunameValueText)).setText(green.getCufullname());

            ((TextView) mHeaderView.findViewById(R.id.weightValueText)).setText(green.getWeight() + "");
            if (green.getGreenstatus() == 1) {
                ((TextView) mHeaderView.findViewById(R.id.statusValueText)).setText("可提货");
                ((TextView) mHeaderView.findViewById(R.id.statusValueText)).setTextColor(context.getResources().getColor(R.color.mainColor));

            } else {
                ((TextView) mHeaderView.findViewById(R.id.statusValueText)).setText("未缴款，提货前请先缴款");
                ((TextView) mHeaderView.findViewById(R.id.statusValueText)).setTextColor(context.getResources().getColor(R.color.secondColor));
            }
        } else {
            DtList dtList = green.getDtList().get(position - 1);
            ((ListHolder) holder).idText.setText(dtList.getGrade());
            ((ListHolder) holder).weightText.setText(dtList.getGreennum() + "");
            ((ListHolder) holder).typeText.setText(dtList.getHandCode());
            ((ListHolder) holder).contentText.setText(dtList.getParea());
        }
    }

    @Override
    public int getItemCount() {
        return green.getDtList().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    //在这里面加载ListView中的每个item的布局
    class ListHolder extends RecyclerView.ViewHolder {
        TextView typeText;
        TextView weightText;
        TextView idText;
        TextView contentText;

        public ListHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            typeText = itemView.findViewById(R.id.typeText);
            weightText = itemView.findViewById(R.id.weightText);
            idText = itemView.findViewById(R.id.idText);
            contentText = itemView.findViewById(R.id.contentText);
        }
    }
}
