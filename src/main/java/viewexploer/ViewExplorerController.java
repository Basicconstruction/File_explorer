package viewexploer;

import javax.swing.*;

public class ViewExplorerController implements Controller{
    private JFrame parent;
    private LiteViewExplorer lfe;
    private ViewHolder viewHolder;
    public ViewExplorerController(String type){
        ViewHolder viewHolder = new NormalBigIconViewHolder();
        ((NormalBigIconViewHolder) viewHolder).setPanelSize(LiteViewExplorer.defaultWidth, LiteViewExplorer.defaultHeight);
        LiteViewExplorer lfe = new LiteViewExplorer(this.getParentFrame(),viewHolder,910,530);
        setLiteViewExplorer(lfe);
        setViewHolder(viewHolder);//后

    }
    public void setLiteViewExplorer(LiteViewExplorer lfe){
        this.lfe = lfe;
    }
    public LiteViewExplorer getLiteFileExplorer(){
        return this.lfe;
    }
    public ViewHolder getViewHolder(){
        return this.viewHolder;
    }
    public void setViewHolder(ViewHolder viewHolder){
        this.viewHolder = viewHolder;
        this.viewHolder.setController(this);
        this.viewHolder.setParentPane(this.lfe);
    }
    @Override
    public JFrame getParentFrame(){
        return this.parent;
    }
    @Override
    public void setParentFrame(JFrame parent) {
        this.parent = parent;
    }
}
