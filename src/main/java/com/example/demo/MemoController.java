package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemoController {

    // Listの代わりに、Repositoryを注入（依存性注入：DI）してもらう
    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // ① 一覧表示（GET）
    @GetMapping("/")
    public String index(Model model) {
        // データベースから全件データを取ってくる！
        List<Memo> memoList = memoRepository.findAll();
        model.addAttribute("memos", memoList);
        return "index";
    }

    // ② 追加（POST）
    @PostMapping("/add")
    public String addMemo(@RequestParam("content") String content) {
        if (content != null && !content.trim().isEmpty()) {
            // 新しいMemoオブジェクトを作ってDBに保存（save）する
            Memo memo = new Memo(content);
            memoRepository.save(memo);
        }
        return "redirect:/";
    }

    // ③ 削除（DELETE）
    @GetMapping("/delete/{id}")
    public String deleteMemo(@PathVariable("id") Long id) {
        // IDを指定してDBから削除する
        memoRepository.deleteById(id);
        return "redirect:/";
    }

    // ④ 編集画面表示（GET）
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        // IDで1件探す（見つからなければトップへ）
        Memo memo = memoRepository.findById(id).orElse(null);
        if (memo != null) {
            model.addAttribute("memo", memo);
            return "edit";
        }
        return "redirect:/";
    }

    // ⑤ 更新処理（POST）
    @PostMapping("/update/{id}")
    public String updateMemo(@PathVariable("id") Long id, @RequestParam("content") String content) {
        if (content != null && !content.trim().isEmpty()) {
            // 既存のデータを取得して中身を書き換えて保存（上書き）
            Memo memo = memoRepository.findById(id).orElse(null);
            if (memo != null) {
                memo.setContent(content);
                memoRepository.save(memo);
            }
        }
        return "redirect:/";
    }
}