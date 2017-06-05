package com.zhou.myweather.module.weather.adapter;

/**
 * Created by Zhou0618 on 2016/4/27 0027.
 */
public class HotCityAdapter {

//
//        extends BaseRecycleViewAdapter<HotCityAdapter.ViewHolder> {
//
//    private List<String> cityNames;
//    private Context mContext;
//
//    public HotCityAdapter() {
//    }
//
//    public HotCityAdapter(Context mContext) {
//        this.mContext = mContext;
//        cityNames = new ArrayList<String>();
//    }
//
//    public void setData(List<String> cityNames) {
//        if (this.cityNames != null)
//            this.cityNames.clear();
//        this.cityNames.addAll(cityNames);
//        this.notifyDataSetChanged();
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citys, null);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public int getItemCount() {
//        if (cityNames == null) {
//            return 0;
//        }
//        return cityNames.size();
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position, List payloads) {
//        super.onBindViewHolder(holder, position, payloads);
//        String cityName = cityNames.get(position);
//        holder.itemView.setTag(cityNames);
//        holder.cityName.setText(cityName);
//    }
//
//    class ViewHolder extends BaseRecycleViewHoldler {
//        @Bind(R.id.cityName)
//        TextView cityName;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
}
