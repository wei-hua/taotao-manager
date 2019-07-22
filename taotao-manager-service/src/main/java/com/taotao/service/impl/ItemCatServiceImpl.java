package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
  @Autowired
  TbItemCatMapper tbItemCatMapper;

  @Override
  public List<EasyUITreeNode> getItemCatList(long id) {
    List<EasyUITreeNode> easyUITreeNodeList = new ArrayList<>();
    TbItemCatExample tbItemCatExample = new TbItemCatExample();
    tbItemCatExample.createCriteria().andParentIdEqualTo(id);
    List<TbItemCat> catList = tbItemCatMapper.selectByExample(tbItemCatExample);
    for (TbItemCat catItem:catList) {
      EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
      easyUITreeNode.setId(catItem.getId());
      easyUITreeNode.setText(catItem.getName());
      easyUITreeNode.setState(catItem.getIsParent()?"closed":"open");
      easyUITreeNodeList.add(easyUITreeNode);
    }
    return easyUITreeNodeList;
  }
}
