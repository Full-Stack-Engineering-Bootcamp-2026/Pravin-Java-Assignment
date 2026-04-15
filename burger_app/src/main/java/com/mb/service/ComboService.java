package com.mb.service;

import com.mb.dto.ComboInDto;
import com.mb.entities.Combo;
import java.util.List;

public interface ComboService {
  Combo saveCombo(ComboInDto dto);

  List<Combo> findCombos();
}
