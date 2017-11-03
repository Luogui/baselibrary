package com.android.luogui.baselibrary.LayerAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * describe
 * Created by LuoGui on 2017/4/10.
 */

public abstract class LayerAdapter extends RecyclerView.Adapter{



    public abstract int getGroupCount();

    public abstract int getChildrenCount(int groupPosition);

    public abstract RecyclerView.ViewHolder onCreateTopGroupViewHolder(ViewGroup parent);

    public abstract RecyclerView.ViewHolder onCreateBottomGroupViewHolder(ViewGroup parent);

    public abstract RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindTopGroup(RecyclerView.ViewHolder holder, int groupPosition);

    public abstract void onBindBottomGroup(RecyclerView.ViewHolder holder, int groupPosition);

    public abstract void onBindChild(RecyclerView.ViewHolder holder,
                                     int groupPosition, int childPosition, int viewType);


    private int[] groupPos;
    private OnGroupClickListener onGroupClickListener;
    private OnChildClickListener onChildClickListener;


    /**
     * 从0开始,0为组top布局,-1为组bottom布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        LayerIndexPath path = getPos(position);
        if (path.getChild() == -1) {
            return 0;
        } else if (path.isBottom) {
            return -1;
        }
        return getChildViewType(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return onCreateTopGroupViewHolder(parent);
        } else if (viewType == -1) {
            return onCreateBottomGroupViewHolder(parent);
        } else {
            return onCreateChildViewHolder(parent, viewType);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final LayerIndexPath path = getPos(position);
        if (path.getChild() == -1) {
            onBindTopGroup(holder, path.getGroup());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onGroupClickListener != null) {
                        onGroupClickListener.onGroupClick(holder.itemView, path.getGroup(), true);
                    }
                }
            });
        } else if (path.isBottom) {
            onBindBottomGroup(holder, path.getGroup());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onGroupClickListener != null) {
                        onGroupClickListener.onGroupClick(holder.itemView, path.getGroup(), false);
                    }
                }
            });
        } else {
            onBindChild(holder, path.getGroup(), path.getChild(), getItemViewType(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onChildClickListener != null) {
                        onChildClickListener.onChildClick(holder.itemView, path.getGroup(), path.getChild());
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        groupPos = new int[getGroupCount() + 1];
        int count = 0;
        groupPos[0] = count;
        for (int i = 0; i < getGroupCount(); i++) {

            count++;//top
            count += getChildrenCount(i);
            count++;//bottom
            groupPos[i + 1] = count;
        }
        return count;
    }

    public int getChildViewType(int position) {
        return 1;
    }

    //位置判定
    private LayerIndexPath getPos(int position) {
        LayerIndexPath index = new LayerIndexPath();
        for (int i = 0; i < groupPos.length - 1; i++) {
            if (position < groupPos[i + 1]) {
                index.setGroup(i);
                index.setChild(position - groupPos[i] - 1);
                index.setBottom(position == groupPos[i + 1] - 1);
                break;
            }
        }
        return index;
    }

    protected void bindGroupClick(final View view, final int group, final boolean isTop) {
        if (onGroupClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGroupClickListener.onGroupClick(view, group, isTop);
                }
            });

        }
    }

    protected void bindChildClick(final View view, final int group, final int child) {
        if (onChildClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildClickListener.onChildClick(view, group, child);
                }
            });

        }
    }


    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListener = onGroupClickListener;
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }
}
