package fi.eelij.Darkholme.Util;

@SuppressWarnings("unchecked")
public class UniqueList<T> extends CustomList<T> {
    @Override
    public boolean add(T obj) {
        checkSize();

        if (!contains(obj)) {
            this.list[pointer] = obj;
            this.pointer++;

            return true;
        }

        return false;
    }

    public boolean contains(T obj) {
        for (int i = 0; i < this.pointer; i++) {
            if (this.list[i].equals(obj) || this.list[i] == obj) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean remove(T obj) {
        boolean removed = false;

        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] == null) {
                continue;
            }

            if (this.list[i].equals(obj) || this.list[i] == obj) {
                this.list[i] = null;
                removed = true;
                pointer--;
                i++;
            }

            if (removed && i <= this.list.length) {
                this.list[i - 1] = this.list[i];
            }
        }

        shrink();

        return removed;
    }
}
