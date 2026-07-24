package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 画面ではなく「データ（JSON）」を直接返すコントローラー
@RequestMapping("/api/memos")
public class MemoApiController {

    private final MemoRepository memoRepository;

    public MemoApiController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // ① 全件のメモをJSONで返す
    @GetMapping
    public List<Memo> getAllMemos() {
        return memoRepository.findAll();
    }

    // ② 非同期でメモを追加して、保存されたデータを返す
    @PostMapping
    public Memo createMemo(@RequestBody Memo memo) {
        if (memo.getContent() != null && !memo.getContent().trim().isEmpty()) {
            return memoRepository.save(memo);
        }
        return null;
    }

    // ③ 非同期でメモを削除する
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable("id") Long id) {
        memoRepository.deleteById(id);
    }
}