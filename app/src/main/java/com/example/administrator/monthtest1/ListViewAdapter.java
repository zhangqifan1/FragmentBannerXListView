package com.example.administrator.monthtest1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */

public class ListViewAdapter extends BaseAdapter {
    private Bean bean;
    private Context context;
    private final List<String> imageUrl;

    public ListViewAdapter(Context context, Bean bean) {
        imageUrl = new ArrayList<>();
        imageUrl.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1511928397,1744182508&fm=28&gp=0.jpg");
        imageUrl.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=49292017,22064401&fm=28&gp=0.jpg");
        imageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1303680113,133301350&fm=117&gp=0.jpg");
        this.context = context;
        this.bean = bean;
    }


    final int Type0=0;
    final int Type1=1;
    final int Type2=2;
    final int Type3=3;

    @Override
    public int getCount() {
        return bean.getItemList().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return Type0;
        }else if(position==1){
            return  Type1;
        }else{
            int type=position%2;
            switch(type){
                case 0:
                    return Type2;
                case 1:
                    return Type3;
                default:
                    break;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        int type = getItemViewType(i);
        if(view==null){
            holder=new ViewHolder();
            switch(type){
                case Type0://返ViewPager
                    view=view.inflate(context,R.layout.item1,null);
                    holder.banner= (Banner) view.findViewById(R.id.b);
                    break;
                case Type1://返热门
                    view=view.inflate(context,R.layout.item2,null);
                    holder.textView= (TextView) view.findViewById(R.id.tv_item2);
                    break;
                case Type2://图片
                    view=view.inflate(context,R.layout.item3,null);
                    holder.imageView= (ImageView) view.findViewById(R.id.image_item3);
                    break;
                case Type3://图标+text
                    view=view.inflate(context,R.layout.item4,null);
                    holder.imageView4= (ImageView) view.findViewById(R.id.image4);
                    holder.textView4= (TextView) view.findViewById(R.id.tv_item4);
                    break;
                default:
                    break;
            }
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
            switch(type){
                case Type0://返ViewPager
                    holder.banner.setImageLoader(new GlideImageLoader());
                    holder.banner.setImages(imageUrl);
                    holder.banner.setDelayTime(1000);
                    holder.banner.setOnBannerClickListener(new OnBannerClickListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            switch(position){
                                case 1:
                                    Toast.makeText(context,"第一个图",Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Toast.makeText(context,"第二个图",Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Toast.makeText(context,"第三个图",Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    holder.banner.start();
                    break;
                case Type1://返热门
                    holder.textView.setText("近期热门");
                    break;
                case Type2://图片
                    ImageLoaderUtils.setImageView("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=49292017,22064401&fm=28&gp=0.jpg",context,holder.imageView);
                    break;
                case Type3://图标+text
                    holder.textView4.setText("数据");
                    ImageLoaderUtils.setImageView("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=49292017,22064401&fm=28&gp=0.jpg",context,holder.imageView4);
                    break;
                default:
                    break;
            }
        }
        return view;
    }
    static class ViewHolder{
        Banner banner;
        TextView textView;
        ImageView imageView;
        TextView textView4;
        ImageView imageView4;
    }

}
