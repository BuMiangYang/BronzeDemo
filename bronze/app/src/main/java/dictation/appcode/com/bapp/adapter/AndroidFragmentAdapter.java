package dictation.appcode.com.bapp.adapter;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import dictation.appcode.com.bapp.GlideApp;
import dictation.appcode.com.bapp.R;
import dictation.appcode.com.bapp.base.BaseActivity;
import dictation.appcode.com.bapp.entity.WorkInfo;

/**
 * 描述：Android 网格适配
 */

public class AndroidFragmentAdapter extends BaseQuickAdapter<WorkInfo, BaseViewHolder> {


    public AndroidFragmentAdapter(@Nullable List<WorkInfo> data) {
        super(R.layout.item_android_index, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkInfo item) {

        ImageView imageView = helper.getView(R.id.ivHeader);

        Display display = ((BaseActivity) mContext).getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);

        // 动态设置了大小
        ViewGroup.LayoutParams para = imageView.getLayoutParams();
        para.width = p.x / 3;
        para.height = p.x / 3;
        imageView.setLayoutParams(para);
        //设置边距（此处是考虑到最后一个添加+ 号时候 填充不好看 所以设置 本DEMO无用）
        imageView.setPadding(0, 0, 0, 0);

        GlideApp.with(mContext)
                .load(R.mipmap.ic_def)
                .error(R.mipmap.ic_main_head)
                .override(p.x / 3, p.x / 3)
                .centerCrop()
                .into(imageView);
        helper
                .setText(R.id.tvTitle,item.title)
                .setText(R.id.tvDescription,item.description);
    }

}
