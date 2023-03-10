package cc.ficc.showlist.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.ArrayList;

import cc.ficc.showlist.ICallback;
import cc.ficc.showlist.R;
import cc.ficc.showlist.ShowView;
import cc.ficc.showlist.bean.AppBean;

public class HorizontalDisplay extends ShowView implements ShowView.Subassembly {
    private ICallback iCallback;

    public HorizontalDisplay(Context context) {
        super(context);
        init(this);
        var linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
    }

    @Override
    public void loadData(ArrayList<AppBean> appBeans, ICallback iCallback) {
        this.iCallback = iCallback;
        setAdapter(new ShowView.Adapter(appBeans));
    }

    @Override
    public View onCreateItemView() {
        return LayoutInflater.from(getContext())
                .inflate(R.layout.show_item_horizontal1, this, false);
    }

    @Override
    public void convert(@NonNull BaseViewHolder baseViewHolder, AppBean appBean) {
        LinearLayout linearLayout = baseViewHolder.findView(R.id.progress_horizontal1);
        ImageView imageView = baseViewHolder.findView(R.id.imageView_horizontal1);
        TextView textView = baseViewHolder.findView(R.id.textView_horizontal1);
        TextView textView2 = baseViewHolder.findView(R.id.textView2_horizontal1);
        Glide.with(getContext())
                .load(appBean.getAppImage())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(imageView);
        if (appBean.getTitle() == null) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(appBean.getTitle());
        }
        if (appBean.getSubTitle() == null) {
            textView2.setVisibility(View.GONE);
        } else {
            textView2.setText(appBean.getSubTitle());
        }
        linearLayout.setOnClickListener(view -> {
            iCallback.itemClicked(view, appBean.getDownloadLink());
        });
    }
}
