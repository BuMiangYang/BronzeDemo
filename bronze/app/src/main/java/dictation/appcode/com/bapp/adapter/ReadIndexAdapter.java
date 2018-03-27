package dictation.appcode.com.bapp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import dictation.appcode.com.bapp.R;
import dictation.appcode.com.bapp.entity.WorkInfo;

/**
 * 描述：文章列表适配
 */

public class ReadIndexAdapter extends BaseQuickAdapter<WorkInfo,BaseViewHolder>{


    public ReadIndexAdapter(@Nullable List<WorkInfo> data) {
        super(R.layout.item_read_index,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkInfo item) {

        helper.setText(R.id.tvTitle,item.title)
                .setText(R.id.tvDescription,item.description);
    }
}
