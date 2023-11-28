package com.kamesuta.itemtranslationgenerator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

// プラグインのメインクラス
public class ItemTranslationGenerator extends JavaPlugin {

    private JsonObject langObject;
    private File inFolder;
    private File outFolder;

    // プラグインが有効化されたときに呼ばれるメソッド
    @Override
    public void onEnable() {
        // 翻訳ファイルを読み込む
        File langFile = new File(getDataFolder(), "translation.json");
        JsonParser jsonParser = new JsonParser();
        try {
            langObject = jsonParser.parse(new FileReader(langFile)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 入力フォルダーを作成する
        inFolder = new File(getDataFolder(), "in");
        inFolder.mkdir();

        // 出力フォルダーを作成する
        outFolder = new File(getDataFolder(), "out");
        outFolder.mkdir();

        // アイテムを翻訳する
        translateItem();
        // ポーションを翻訳する
        translatePotion();
        // エンチャントを翻訳する
        translateEnchant();
    }

    private void translateItem() {
        // itemi18n.ymlを読み込む
        File itemFile = new File(inFolder, "itemi18n.yml");
        YamlConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

        // itemi18n_jp.ymlを作成する
        File itemJpFile = new File(outFolder, "itemi18n.yml");
        YamlConfiguration itemJpConfig = new YamlConfiguration();

        // itemi18n.ymlのキーをループする
        for (String key : itemConfig.getConfigurationSection("itemi18n").getKeys(false)) {
            // ログにキーを表示する
            getLogger().info(key);

            // commentの場合はスキップする
            if (key.equals("comment")) {
                continue;
            }

            // Materialキーを取得する
            Material material = Material.valueOf(key);

            String japaneseName;

            // MaterialキーをtranslationKeyに変換する
            String translationKey = I18n.getMaterialTranslationKey(material);

            // ログにtranslationKeyを表示する
            getLogger().info(translationKey);

            // translationKeyが存在しない場合はValueを使う
            if (translationKey != null) {
                // translationKeyを日本語に変換する
                japaneseName = langObject.get(translationKey).getAsString();
            } else {
                // Valueを使う
                japaneseName = itemConfig.getString("itemi18n." + key);

                // 警告を表示する
                getLogger().warning("！！！！！！！！！！！！！！translationKey is null！！！！！！！！！！！！！！！");
            }

            // itemi18n_jp.ymlに保存する
            itemJpConfig.set("itemi18n." + key, japaneseName);

        }

        // itemi18n_jp.ymlを保存する
        try {
            itemJpConfig.save(itemJpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 完了メッセージを表示する
        getLogger().info("Item translation completed!");
    }

    private void translatePotion() {
        // itemi18n.ymlを読み込む
        File itemFile = new File(inFolder, "potioni18n.yml");
        YamlConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

        // itemi18n_jp.ymlを作成する
        File itemJpFile = new File(outFolder, "potioni18n.yml");
        YamlConfiguration itemJpConfig = new YamlConfiguration();

        // itemi18n.ymlのキーをループする
        for (String key : itemConfig.getConfigurationSection("potioni18n").getKeys(false)) {
            // ログにキーを表示する
            getLogger().info(key);

            // commentの場合はスキップする
            if (key.equals("comment")) {
                continue;
            }

            // Materialキーを取得する
            PotionEffectType potionEffectType = PotionEffectType.getByName(key);

            String japaneseName;

            // MaterialキーをtranslationKeyに変換する
            String translationKey = I18n.getPotionTranslationKey(potionEffectType);

            // ログにtranslationKeyを表示する
            getLogger().info(translationKey);

            // translationKeyが存在しない場合はValueを使う
            if (translationKey != null) {
                // translationKeyを日本語に変換する
                japaneseName = langObject.get(translationKey).getAsString();
            } else {
                // Valueを使う
                japaneseName = itemConfig.getString("itemi18n." + key);

                // 警告を表示する
                getLogger().warning("！！！！！！！！！！！！！！translationKey is null！！！！！！！！！！！！！！！");
            }

            // itemi18n_jp.ymlに保存する
            itemJpConfig.set("itemi18n." + key, japaneseName);

        }

        // itemi18n_jp.ymlを保存する
        try {
            itemJpConfig.save(itemJpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 完了メッセージを表示する
        getLogger().info("Item translation completed!");
    }

    private void translateEnchant() {
        // itemi18n.ymlを読み込む
        File itemFile = new File(inFolder, "enchi18n.yml");
        YamlConfiguration itemConfig = YamlConfiguration.loadConfiguration(itemFile);

        // itemi18n_jp.ymlを作成する
        File itemJpFile = new File(outFolder, "enchi18n.yml");
        YamlConfiguration itemJpConfig = new YamlConfiguration();

        // itemi18n.ymlのキーをループする
        for (String key : itemConfig.getConfigurationSection("enchi18n").getKeys(false)) {
            // ログにキーを表示する
            getLogger().info(key);

            // commentの場合はスキップする
            if (key.equals("comment")) {
                continue;
            }

            // Materialキーを取得する
            @Nullable Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(key.toLowerCase(Locale.ENGLISH)));

            String japaneseName;

            // MaterialキーをtranslationKeyに変換する
            String translationKey = I18n.getEnchantTranslationKey(enchantment);

            // ログにtranslationKeyを表示する
            getLogger().info(translationKey);

            // translationKeyが存在しない場合はValueを使う
            if (translationKey != null) {
                // translationKeyを日本語に変換する
                japaneseName = langObject.get(translationKey).getAsString();
            } else {
                // Valueを使う
                japaneseName = itemConfig.getString("itemi18n." + key);

                // 警告を表示する
                getLogger().warning("！！！！！！！！！！！！！！translationKey is null！！！！！！！！！！！！！！！");
            }

            // itemi18n_jp.ymlに保存する
            itemJpConfig.set("itemi18n." + key, japaneseName);

        }

        // itemi18n_jp.ymlを保存する
        try {
            itemJpConfig.save(itemJpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 完了メッセージを表示する
        getLogger().info("Item translation completed!");
    }

    // I18nクラス
    static class I18n {

        // MaterialキーをtranslationKeyに変換するメソッド
        @SuppressWarnings("removal")
        public static String getMaterialTranslationKey(Material material) {
            // MaterialからtranslationKeyを取得する
            return material.getTranslationKey();
        }

        // MaterialキーをtranslationKeyに変換するメソッド
        public static String getPotionTranslationKey(PotionEffectType potionEffectType) {
            // MaterialからtranslationKeyを取得する
            return "effect.minecraft." + potionEffectType.getKey().getKey();
        }

        // MaterialキーをtranslationKeyに変換するメソッド
        public static String getEnchantTranslationKey(Enchantment enchantment) {
            // MaterialからtranslationKeyを取得する
            return "enchantment.minecraft." + enchantment.getKey().getKey();
        }
    }
}
