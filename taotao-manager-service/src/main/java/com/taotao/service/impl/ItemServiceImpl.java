package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  private TbItemMapper tbItemMapper;
  @Autowired
  private TbItemDescMapper tbItemDescMapper;

  @Override
  public TbItem getItemById(long itemId) {
    TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
    return tbItem;
  }

  @Override
  public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
    TbItemExample example = new TbItemExample();
    example.createCriteria().andIdLessThan(new Long(907012));
    List<TbItem> list = tbItemMapper.selectByExample(example);
    EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
    easyUIDataGridResult.setRows(list);
    easyUIDataGridResult.setTotal(30);
    return easyUIDataGridResult;
  }

  @Override
  public TaotaoResult addItem(TbItem item, String desc) {
    long itemId = IDUtils.genItemId();
    item.setId(itemId);
    //商品状态，1-正常，2-下架，3-删除
    item.setStatus((byte) 1);
    item.setCreated(new Date());
    item.setUpdated(new Date());
    tbItemMapper.insert(item);

    TbItemDesc tbItemDesc = new TbItemDesc();
    tbItemDesc.setItemId(itemId);
    tbItemDesc.setItemDesc(desc);
    tbItemDesc.setCreated(new Date());
    tbItemDesc.setUpdated(new Date());
    tbItemDescMapper.insert(tbItemDesc);
    return TaotaoResult.ok();
  }
}
