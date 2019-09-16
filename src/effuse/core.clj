(ns effuse.core)

(defonce ^:dynamic *-effects-* {})

(defn handle [f config]
  (fn [& args]
    (binding [*-effects-* (merge *-effects-* config)]
      (apply f args))))

(defn perform [k & args]
  (let [x (*-effects-* k)]
    (if (nil? x)
      (throw (ex-info (str "Unhandled effect " k)
                      {:effect k
                       :args args}))
      (cond (fn? x) (apply x args)
            :else x))))