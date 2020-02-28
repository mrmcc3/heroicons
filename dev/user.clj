(ns user
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str])
  (:import (org.jsoup Jsoup)))

(defn tag [element]
  (-> element .tagName keyword))

(defn assoc-attr [m attr]
  (assoc m (-> attr .getKey keyword) (.getValue attr)))

(defn attr-map [element]
  (reduce assoc-attr {} (.attributes element)))

(defn element->data [element]
  (into
    [(tag element) (attr-map element)]
    (map element->data)
    (.children element)))

(defn icon-symbol [file]
  (-> file .getName
      (str/split #"\.") first
      (str/split #"-" 2) second symbol))

(defn icon-form [file]
  (let [icon (icon-symbol file)
        arg  'attr-map
        doc  (str icon " heroicon data. Optionally pass an attr-map to merge with the svg attributes")
        data (-> file slurp Jsoup/parseBodyFragment
                 (.getElementsByTag "svg")
                 first element->data
                 (update 1 (fn [attrs] `(~'merge ~attrs ~arg))))]
    `(~'defn ~icon ~doc ([] (~icon {})) ([~arg] ~data))))

(defn ns-form [ns]
  (let [excludes '[filter]]
    `(~'ns ~ns (:refer-clojure :exclude ~excludes))))

(defn spit-icons [dir ns]
  (let [ns-file    (io/file "src-clj" (str (str/replace ns "." "/") ".cljc"))
        icon-files (file-seq (io/file "dist" dir))]
    (println "Generating" ns "...")
    (io/make-parents ns-file)
    (with-open [writer (io/writer ns-file)]
      (.write writer (pr-str (ns-form ns)))
      (.newLine writer)
      (doseq [icon-file icon-files]
        (when (.isFile icon-file)
          (.write writer (pr-str (icon-form icon-file)))
          (.newLine writer))))))

(defn -main [& args]
  (spit-icons "outline-md" 'mrmcc3.heroicons.outline.md)
  (spit-icons "solid-sm" 'mrmcc3.heroicons.solid.sm)
  (println "done!"))

(comment

  (-main)

  (require '[mrmcc3.heroicons.outline.md :as heroicons])

  (heroicons/arrow-circle-right)

  (heroicons/shield-check {:class "h-5"})

  )



